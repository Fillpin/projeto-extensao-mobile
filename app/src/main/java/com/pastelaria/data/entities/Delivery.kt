// Entidade para Controle de Entregas
@Entity(tableName = "deliveries")
data class Delivery(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val supplierName: String, // "Fornecedor de Pastéis" ou "Fornecedor de Açaí"
    val deliveryDate: String,
    val status: String, // "Pendente" ou "Concluído"
    val signedReceiptUri: String? = null, // URI do PDF assinado
    val pastryDoughQuantity: Int = 0, // 500 massas (para pastéis)
    val acaiQuantity: Int = 0, // 50kg (5 baldes de 10kg)
    // Itens adicionais para pastéis (exemplo: queijo mussarela)
    val additionalPastelItems: Map<String, Int> = emptyMap(),
    // Frutas e adicionais para açaí (exemplo: banana = 5kg)
    val acaiFruits: Map<String, Int> = emptyMap(),
    val acaiAdditives: Map<String, Int> = emptyMap()
)

// Entidade para Controle de Estoque
@Entity(tableName = "inventory")
data class InventoryItem(
    @PrimaryKey val ingredientName: String,
    val type: String, // "Pastel" ou "Açaí"
    var quantity: Int
)

// Entidade para Controle de Vendas
@Entity(tableName = "sales")
data class Sale(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val productType: String, // "Pastel" ou "Açaí"
    val saleType: String, // "A", "B", ou "C"
    val customerName: String,
    val paymentMethod: String, // "PIX", "Cartão", etc.
    val ingredients: List<String>, // Ingredientes selecionados
    val sauces: List<String>, // Molhos/caldas
    val date: String
)
