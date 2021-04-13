package com.planview.server.service;

import java.util.List;

import javax.transaction.Transactional;

import com.planview.server.entity.UserRole;
import com.planview.server.entity.UserView;
import com.planview.server.repos.TimesheetRepo;
import com.planview.server.repos.UserRepo;
import com.planview.server.repos.WorkAssignmentRepo;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Transactional
@Service
public class UserService {
    private final UserRepo userRepo;
    private final TimesheetRepo timesheetRepo;
    private final WorkAssignmentRepo workAssignmentRepo;

    public UserService(UserRepo userRepo, TimesheetRepo timesheetRepo, WorkAssignmentRepo workAssignmentRepo) {
        this.userRepo = userRepo;
        this.timesheetRepo = timesheetRepo;
        this.workAssignmentRepo = workAssignmentRepo;
    }

    public List<UserView> findAllExceptMe() {
        var userId = AuthService.getCurrentUserId();
        return this.userRepo.findAllExcept(userId);
    }

    public UserView updateUser(UserView view) {
        var userOpt = this.userRepo.findById(view.getId());
        if (userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        var user = userOpt.get();
        if (!view.isLocked()) {
            user.setLocked(false);
            user.setFailedTries(0);
        }

        user.setRole(view.isAdmin() ? UserRole.ADMIN : UserRole.USER);
        user.setEmailId(view.getEmailId());

        user = this.userRepo.save(user);
        return new UserView(user);
    }

    public void deleteUser(int userId) {
        var userOpt = this.userRepo.findById(userId);
        if (userOpt.isEmpty()) {
            return;
        }

        this.timesheetRepo.deleteAllByUserId(userId);
        this.workAssignmentRepo.deleteAllByUserId(userId);
        this.userRepo.deleteById(userId);
    }
}
