package com.project.service.base;

import com.project.repository.FunctionRepository;
import com.project.repository.ProjectRepository;
import com.project.repository.RoleRepository;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 2017/12/28.
 * Base : 是Service的基类
 */
@Service
public class BaseService {
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public RoleRepository roleRepository;
    @Autowired
    public FunctionRepository functionRepository;
    @Autowired
    public ProjectRepository projectRepository;
}
