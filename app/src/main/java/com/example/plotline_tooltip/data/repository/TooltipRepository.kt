import androidx.lifecycle.LiveData
import com.example.plotline_tooltip.data.db.TooltipDao
import com.example.plotline_tooltip.data.model.TooltipDataEntity

class TooltipDataRepository(private val tooltipDao: TooltipDao) {
    val allTooltipData: LiveData<List<TooltipDataEntity>> = tooltipDao.getAllTooltipData()

    val allTooltiData: LiveData<List<TooltipDataEntity>> = tooltipDao.getAllTooltipData()
    suspend fun insertTooltipData(tooltipData: TooltipDataEntity) {
        tooltipDao.insertTooltipData(tooltipData)
    }

    suspend fun updateTooltipData(tooltipData: TooltipDataEntity) {
        tooltipDao.updateTooltipData(tooltipData)
    }
}