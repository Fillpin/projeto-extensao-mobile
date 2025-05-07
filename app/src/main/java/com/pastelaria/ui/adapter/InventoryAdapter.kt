class InventoryAdapter(private val items: List<InventoryItem>) : RecyclerView.Adapter<InventoryAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemInventoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemInventoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.txtIngredient.text = item.ingredientName
        holder.binding.txtType.text = item.type
        holder.binding.txtQuantity.text = item.quantity.toString()
    }

    override fun getItemCount() = items.size
}

// No Fragment:
binding.recyclerViewPastel.adapter = InventoryAdapter(pastelItems)
binding.recyclerViewAcai.adapter = InventoryAdapter(acaiItems)
