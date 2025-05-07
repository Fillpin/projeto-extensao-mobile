class SalesFragment : Fragment() {
    private lateinit var binding: FragmentSalesBinding
    private val viewModel: InventoryViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSalesBinding.inflate(inflater, container, false)
        setupUI()
        return binding.root
    }

    private fun setupUI() {
        // Botão para vender pastel
        binding.btnSellPastel.setOnClickListener {
            showIngredientSelectionDialog("Pastel")
        }

        // Botão para vender açaí
        binding.btnSellAcai.setOnClickListener {
            showIngredientSelectionDialog("Açaí")
        }
    }

    private fun showIngredientSelectionDialog(productType: String) {
        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle("Selecione os Ingredientes")

        // Lógica para escolha de ingredientes baseada no tipo (A, B, C)
        val ingredients = if (productType == "Pastel") {
            listOf("Queijo Mussarela", "Calabresa", "Catupiry", ...)
        } else {
            listOf("Granola", "Leite Condensado", "Morango", ...)
        }

        val selectedItems = mutableListOf<String>()
        dialog.setMultiChoiceItems(ingredients.toTypedArray(), null) { _, which, isChecked ->
            if (isChecked) selectedItems.add(ingredients[which])
        }

        dialog.setPositiveButton("Confirmar") { _, _ ->
            if (viewModel.checkStock(selectedItems, productType)) {
                // Registrar venda no banco de dados
                val sale = Sale(
                    productType = productType,
                    saleType = "A", // Definir com base na quantidade selecionada
                    customerName = binding.edtCustomerName.text.toString(),
                    paymentMethod = binding.spinnerPayment.selectedItem.toString(),
                    ingredients = selectedItems,
                    sauces = listOf(), // Adaptar para molhos
                    date = SimpleDateFormat("dd/MM/yyyy").format(Date())
                )
                viewModel.registerSale(sale)
                updateStockAfterSale(selectedItems, productType)
            } else {
                Toast.makeText(context, "Estoque insuficiente!", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }

    private fun updateStockAfterSale(ingredients: List<String>, type: String) {
        ingredients.forEach { ingredient ->
            viewModel.updateInventory(ingredient, type, -1)
        }
    }
}
