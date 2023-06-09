import androidx.lifecycle.LiveData
import com.example.plotline_tooltip.data.db.TooltipDao
import com.example.plotline_tooltip.data.model.TooltipDataEntity

class TooltipDataRepository(private val tooltipDataDao: TooltipDao) {
    val allTooltipData: LiveData<List<TooltipDataEntity>> = tooltipDataDao.getAllTooltipData()

    suspend fun insertTooltipData(tooltipData: TooltipDataEntity) {
        tooltipDataDao.insertTooltipData(tooltipData)
    }

    suspend fun deleteTooltipData(buttonId: String) {
        tooltipDataDao.deleteTooltipData(buttonId)
    }
}