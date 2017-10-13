node {
	def app 	
	try {
		stage 'Build and Unit Test'
			checkout scm
			sh 'chmod 755 ./gradlew'
			sh './gradlew clean build'
			println "Current build result: " + currentBuild?.result
			
		stage 'Build Docker Image' 
				sh './gradlew buildDocker'
				
		stage 'Archive Reports'
			// place holder
			
		stage 'Publish Image'
			def branches = ['master', 'vignesh-rest']
			def registryUrl = "https://registry.hub.docker.com"
			if((!currentBuild?.result || currentBuild?.result == 'SUCCESS') && branches.contains(env.BRANCH_NAME)) {
				 docker.withRegistry(registryUrl, "docker-hub-credentials") {
				 	println 'Publishing image to docker repository: ' + registryUrl 
					sh 'docker push vpkannan/reviewable'
				 }
			} else {
				println 'Skipping publish image step' 
			}
	} catch(e) {
		println "Exception Occurred in build pipeline: " + e
		currentBuild.result = 'FAILED'
	}
}
