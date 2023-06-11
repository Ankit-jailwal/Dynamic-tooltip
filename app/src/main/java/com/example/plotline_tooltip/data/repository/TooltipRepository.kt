import androidx.lifecycle.LiveData
import com.example.plotline_tooltip.data.db.TooltipDao
import com.example.plotline_tooltip.data.model.TooltipDataEntity

class TooltipRepository(private val tooltipDao: TooltipDao) {
    val allTooltipData: LiveData<List<TooltipDataEntity>> = tooltipDao.getAllTooltipData()

    suspend fun insert(tooltipData: TooltipDataEntity) {
        println("debug2 $tooltipData")
        tooltipDao.insertTooltipData(tooltipData)
    }

    suspend fun update(tooltipData: TooltipDataEntity) {
        tooltipDao.updateTooltipData(tooltipData)
    }
}