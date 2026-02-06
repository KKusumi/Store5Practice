import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.dsl.Lint
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLintConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            when {
                pluginManager.hasPlugin("com.android.application") ->
                    configure<ApplicationExtension> {
                        lint {
                            configureLint()
                        }
                    }

                pluginManager.hasPlugin("com.android.library") ->
                    configure<LibraryExtension> {
                        lint {
                            configureLint()
                        }
                    }

                else -> {
                    pluginManager.apply("com.android.lint")
                    configure<Lint> {
                        configureLint()
                    }
                }
            }
        }
    }

    private fun Lint.configureLint() {
        xmlReport = true
        checkDependencies = true
        disable += setOf("GradleDependency")
    }
}
