package co.innovatespace.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "source")
data class Source(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description : String,
    val url: String,
    val category : String,
)
