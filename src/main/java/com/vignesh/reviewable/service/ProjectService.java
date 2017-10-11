package com.vignesh.reviewable.service;

import java.util.List;

import com.vignesh.reviewable.domain.Project;

public interface ProjectService {

	public List<Project> getAllProjects();

	public Project getProjectById(long id);

	public Project createProject(Project project);
}
