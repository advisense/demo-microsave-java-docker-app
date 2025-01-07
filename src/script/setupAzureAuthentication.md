# Authentication from GitHub til Azure

These commands are used to setup the Github action that deploys updated applications to Azure.
These are based on: <https://learn.microsoft.com/en-us/azure/app-service/deploy-github-actions>

This recipie uses the recommended way to authenticate with Azure App Services for GitHub Actions is with OpenID Connect.

## 0. Prerequisite

You need to know the following:

- subscriptionID
- TenantID
- resourceGroupName
- WebAppName used in Azure

## 1. Create application

```az ad app create --display-name "DemoMicrosaveApplicationDeploymentUser"```

Write down the application ID, this is the same as the Client ID and will be used later in the process.


## 2. create a service principle for your application

```az ad sp create --id <APPLICATION_ID>```

## 3. Create the role assignment

This needs to be done twice, one for each environment (test and prod has uniqe resourcegroups and webAppNames)

```az role assignment create --role contributor --subscription <SUBSCRIPTION_ID> --assignee-object-id  <APPLICATION-OBJECT-ID> --scope subscriptions/<SUBSCRIPTION_ID>/resourceGroups/<RESOURCE_GROUP_NAME>/providers/Microsoft.Web/sites/<WEBAPP_NAME> --assignee-principal-type ServicePrincipal```

## 4. create federated credentials to allow github to use the service principal

First create the file: ```credential.json```

```{
    "name": "DemoMicrosaveAppGithubCredentials",
    "issuer": "https://token.actions.githubusercontent.com",
    "subject": "repo:advisense/demo-microsave-java-docker-app:ref:refs/heads/main",
    "description": "Credentials connected to the DemoMicrosaveApplicationDeploymentUser",
    "audiences": [
        "api://AzureADTokenExchange"
    ]
}```

Then run the command:

```az ad app federated-credential create --id <APPLICATION-OBJECT-ID> --parameters credential.json```

## 5. Configure the Github secret

Defined the following Github secrets:
- ```AZURE_CLIENT_ID``` with the value ```<APPLICATION-OBJECT-ID>```
- ```AZURE_TENANT_ID```
- ```AZURE_SUBSCRIPTION_ID```
