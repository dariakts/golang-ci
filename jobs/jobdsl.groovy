def projects = [
	[ name: 'golang-ci']
]

projects.each {
	def project_name = it.name
	job("Build ${project_name}") {
		wrappers {
			golang('1.11')
		}
		environmentVariables {
		  envs(GOPATH: '$WORKSPACE', GOBIN: '$WORKSPACE/bin')
		}
		scm {
			git("https://github.com/dariakts/${project_name}.git", {node -> node / 'extensions' << '' })
		}
		steps {
			shell('go get -v "."')
			// Dependency problem 
			shell('go get github.com/mitchellh/mapstructure')
			shell('go test -v')
			shell('go build')
		}
	}
}
