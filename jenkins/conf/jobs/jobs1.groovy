#!groovy
println('------------------------------------------------------------------Import Job CI/jobs1')
def pipelineScript = new File('/var/jenkins_config/jobs/jobs1-pipe.groovy').getText("UTF-8")

pipelineJob('CI/jobs1') {
    description("Build .jar from jobs1 java application")
    parameters {
        stringParam {
            name('BRANCH')
            defaultValue('master')
            description("branch to push")
            trim(false)
        }
        booleanParam {
            name('SKIP_TEST')
            defaultValue(true)
            description("Skip test")
        }
        choice {
            name('VERSION_TYPE')
            choices(['SNAPSHOT', 'RELEASE'])
            description('Version type between snapshot and release')
        }
        stringParam {
            name('VERSION')
            defaultValue('1.0')
            description("version of project")
            trim(false)
        }
    }
    definition {
        cps {
            script(pipelineScript)
            sandbox()
        }
    }
}