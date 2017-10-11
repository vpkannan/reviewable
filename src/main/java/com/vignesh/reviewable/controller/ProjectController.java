/**
 * 
 */
package com.vignesh.reviewable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vignesh.reviewable.domain.Project;
import com.vignesh.reviewable.service.ProjectService;

/**
 * @author Vignesh
 *
 */

@RestController
@RequestMapping("projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Project> getAllProjects() {
		// TODO: call project service
		return null;
	}

}
