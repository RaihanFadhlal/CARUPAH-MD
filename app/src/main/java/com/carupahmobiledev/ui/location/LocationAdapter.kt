import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.carupahmobiledev.R
import com.carupahmobiledev.data.remote.response.BankSampahDetail

class LocationAdapter(private var listUser: List<BankSampahDetail>) : RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.nameBs)
        val tvAddress: TextView = view.findViewById(R.id.addressBs)
        val tvPhone: TextView = view.findViewById(R.id.phoneBs)
        val tvEmail: TextView = view.findViewById(R.id.emailBs)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bank_sampah, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = listUser[position].name
        val address = listUser[position].address
        val phone = listUser[position].numberPhone
        val email = listUser[position].email
        holder.tvName.text = name
        holder.tvAddress.text = address
        holder.tvPhone.text = phone
        holder.tvEmail.text = email
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    fun updateData(newList: List<BankSampahDetail>) {
        listUser = newList
        notifyDataSetChanged()
    }
}