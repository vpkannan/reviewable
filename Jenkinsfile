node { 	
	try {
		stage 'Build and Unit Test'
			checkout scm
			sh 'chmod 755 ./gradlew'
			sh './gradlew clean build'
			println "Current build result: " + currentBuild?.result
		
		stage 'Publish Test Results'
			println "Publishing test results to Sonarqube"
			sh './gradlew jacocoTestReport'
			if(isMasterBranch()) {
				sh './gradlew sonarqube'
			} else {
				sh './gradlew sonarqube -Dsonar.projectName=Reviewable-$env.BRANCH_NAME' 
			}
		
		stage 'Build Docker Image' 
			sh './gradlew buildDocker'
			//app = docker.build("vpkannan/reviewable")
			//println 'app value is: ' + app
				
		stage 'Archive Reports'
			// place holder
			
		stage 'Publish Image'
			
			def registryUrl = "https://registry.hub.docker.com"
			if((!currentBuild?.result || currentBuild?.result == 'SUCCESS') && isMasterBranch()) {
				 docker.withRegistry(registryUrl, "docker-hub-credentials") {
				 	println 'Publishing image to docker repository: ' + registryUrl 
            		//app.push("latest")
            		sh './gradlew publishImage'
				 }
			} else {
				println 'Skipping publish image step' 
			}
	} catch(e) {
		println "Exception Occurred in build pipeline: " + e
		currentBuild.result = 'FAILED'
	}
}

def isMasterBranch() {
	 def branches = ['master']
	 branches.contains(env.BRANCH_NAME)
}