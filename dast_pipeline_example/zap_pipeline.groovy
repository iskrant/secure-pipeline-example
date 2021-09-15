node {

// Only once
  // script {
  //   try {
  //     // Context import fails if it already exists
  //     sh 'zap-cli --zap-url http://zap -p 8000 --api-key 1234567132243598543210 --port 8000 context import /zap/data/WebGoat.context'
  //   }
  //   catch (Exception e) {
  //   }
  // }

  script {
    try {
      // If it finds results, returns error code, but we still want to publish the report
      sh 'zap-cli --zap-url http://zap -p 8000 --api-key 1234567132243598543210 quick-scan -c WebGoat -u tester -s all --spider -r http://webgoat:8080/WebGoat'
    }
    catch (Exception e) {
    }
  }

  sh 'zap-cli --zap-url http://zap -p 8000 --api-key 1234567132243598543210 report -o zap_report.html -f html'

  publishHTML([
    allowMissing: false, 
    alwaysLinkToLastBuild: false, 
    keepAll: false, 
    reportDir: '', 
    reportFiles: 'zap_report.html', 
    reportName: 'ZAP DAST Report', 
    reportTitles: ''
  ])

}