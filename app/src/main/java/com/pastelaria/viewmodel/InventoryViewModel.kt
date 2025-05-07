class InventoryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: InventoryRepository = InventoryRepository(application)

    // Atualizar estoque após entrega
    fun processDelivery(delivery: Delivery) {
        when (delivery.supplierName) {
            "Fornecedor de Pastéis" -> {
                // Atualiza massa de pastéis
                repository.updateInventory("Massa Pastel", "Pastel", delivery.pastryDoughQuantity)
                // Atualiza ingredientes adicionais
                delivery.additionalPastelItems.forEach { (item, qty) ->
                    repository.updateInventory(item, "Pastel", qty)
                }
            }
            "Fornecedor de Açaí" -> {
                // Atualiza açaí
                repository.updateInventory("Açaí", "Açaí", delivery.acaiQuantity)
                // Atualiza frutas e adicionais
                delivery.acaiFruits.forEach { (fruit, qty) ->
                    repository.updateInventory(fruit, "Açaí", qty)
                }
                delivery.acaiAdditives.forEach { (additive, qty) ->
                    repository.updateInventory(additive, "Açaí", qty)
                }
            }
        }
    }

    // Verificar estoque ao realizar venda
    fun checkStock(ingredients: List<String>, type: String): Boolean {
        return ingredients.all { ingredient ->
            repository.getInventoryItem(ingredient, type)?.quantity ?: 0 > 0
        }
    }
}
