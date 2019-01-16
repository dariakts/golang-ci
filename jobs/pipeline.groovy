// Configure a Jenkins pipeline
node() {
    def root = tool name: '1.11', type: 'go'
    
    stage('Setup') {
        withEnv(["GOPATH=${WORKSPACE}", "PATH+GO=${root}/bin:${WORKSPACE}/bin", "GOBIN=${WORKSPACE}/bin"]) {
            git "https://github.com/dariakts/golang-ci.git"
	    sh 'go get -v "."'
        }
    }
    
    stage ('Test') {
        withEnv(["GOPATH=${WORKSPACE}", "PATH+GO=${root}/bin:${WORKSPACE}/bin", "GOBIN=${WORKSPACE}/bin"]) {
            sh "go test -v"
        }
    }
    
    stage ('Build') {
        withEnv(["GOPATH=${WORKSPACE}", "PATH+GO=${root}/bin:${WORKSPACE}/bin", "GOBIN=${WORKSPACE}/bin"]) {
            sh "go build"
        }
    }
}
