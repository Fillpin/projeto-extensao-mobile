// Para exibir PDF:
binding.btnViewPdf.setOnClickListener {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        setDataAndType(Uri.parse(delivery.signedReceiptUri), "application/pdf")
        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
    }
    startActivity(intent)
}

// Ordenação na lista:
fun sortDeliveries(sortBy: String) {
    deliveries = when (sortBy) {
        "date" -> deliveries.sortedBy { it.deliveryDate }
        "status" -> deliveries.sortedBy { it.status }
        else -> deliveries
    }
    adapter.notifyDataSetChanged()
}
