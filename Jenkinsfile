env.GRADLE_USER_HOME = "/root/.gradle/${env.JOB_NAME}/.gradle"
	
try {
	stage 'Build and Unit Test'
		checkout scm
		sh 'chmod 755 ./gradlew'
		sh './gradlew clean build'
		
	stage 'Build Docker Image'
		sh './gradlew buildDocker'
		
	stage 'Archive Reports'
		 // Placeholder to archive test reports
		 
	stage 'Publish Image'
		def branches = ['master']
		def registryUrl = "https://hub.docker.com"
		println "Current build result: " + currentBuild?.result
		if((!currentBuild?.result || currentBuild?.result == 'SUCCESS') && branches.contains(env.BRANCH_NAME)) {
			 docker.withRegistry(registryUrl, "Docker Hub") {
			 	println 'Publishing image to docker repository: ' + registryUrl
			 	sh './gradlew publishImage -Ddocker.registry.login.skip=true' 
			 }
		} else {
			println 'Skipping publish image step' 
	}
} catch(e) {
	currentBuild.result = 'FAILED'
}
	