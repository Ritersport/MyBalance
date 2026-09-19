pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MyBalance"
include(":app")
include(":core:DataBase")
include(":core:DesignSystem")
include(":features:AddTransaction")
include(":items:TransactionCard")
include(":items:SettingsItem")
include(":core:Model")
include(":features:TransactionList")
include(":shared-repository:transactionRepository")
include(":shared-repository:categoriesRepository")
include(":shared-repository:accountsRepository")
