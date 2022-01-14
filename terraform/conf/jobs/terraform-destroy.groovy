#!groovy
println('------------------------------------------------------------------Import Job IaC/terraform-destroy')
def pipelineScript = new File('/var/jenkins_config/jobs/terraform-destroy-pipeline.groovy').getText("UTF-8")

pipelineJob('IaC/terraform-destroy') {
    description("destroy instance aws terraform")
    definition {
        cps {
            script(pipelineScript)
            sandbox()
        }
    }
}