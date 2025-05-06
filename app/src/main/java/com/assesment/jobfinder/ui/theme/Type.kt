import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.jobfinder.R

// Custom Font Family definition
// Uncomment and modify this when you add font files to your project
/*
val Montserrat = FontFamily(
    Font(R.font.montserrat_regular),
    Font(R.font.montserrat_medium, FontWeight.Medium),
    Font(R.font.montserrat_semibold, FontWeight.SemiBold),
    Font(R.font.montserrat_bold, FontWeight.Bold),
    Font(R.font.montserrat_italic, style = FontStyle.Italic)
)
*/

// Define any additional text style or typography customizations here
// These can be used in addition to the main Typography.kt file

// Example custom font size
object FontSize {
    val xs = 10
    val sm = 12
    val md = 14
    val lg = 16
    val xl = 18
    val xxl = 20
    val display1 = 24
    val display2 = 30
    val display3 = 36
}

// Job Type Definitions for UI filtering
enum class JobType(val title: String) {
    FULL_TIME("Full-time"),
    PART_TIME("Part-time"),
    CONTRACT("Contract"),
    FREELANCE("Freelance"),
    INTERNSHIP("Internship")
}

// Job Location Type Definitions for UI filtering
enum class LocationType(val title: String) {
    REMOTE("Remote"),
    ON_SITE("On-site"),
    HYBRID("Hybrid")
}

// Job Experience Level for UI filtering
enum class ExperienceLevel(val title: String) {
    ENTRY("Entry Level"),
    MID("Mid Level"),
    SENIOR("Senior Level"),
    EXECUTIVE("Executive")
}
