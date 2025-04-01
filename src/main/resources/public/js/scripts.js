document.addEventListener("DOMContentLoaded", function () {
    // Get elements for cupcake bottom and top selection
    const cupcakeBottomRadios = document.querySelectorAll('input[name="cupcakeBottom"]');
    const cupcakeTopRadios = document.querySelectorAll('input[name="cupcakeTop"]');

    // Initialize variables
    let cupcakeBottomPrice = 0;
    let cupcakeTopPrice = 0;
    let quantity = parseInt(document.getElementById("cupcakeQuantity").value);

    // Function to update the total price
    function updateTotalPrice() {
        const totalPrice = (cupcakeBottomPrice + cupcakeTopPrice) * quantity;
        document.getElementById('totalPrice').textContent = totalPrice.toFixed(2);
    }

    // Event listeners for cupcake bottom selection
    cupcakeBottomRadios.forEach(radio => {
        radio.addEventListener('change', function () {
            cupcakeBottomPrice = parseFloat(this.getAttribute('data-salesprice'));
            updateTotalPrice();
        });
    });

    // Event listeners for cupcake top selection
    cupcakeTopRadios.forEach(radio => {
        radio.addEventListener('change', function () {
            cupcakeTopPrice = parseFloat(this.getAttribute('data-salesprice'));
            updateTotalPrice();
        });
    });

    // Event listeners for the quantity buttons
    const subtractButton = document.getElementById("cupcake-subtract");
    const addButton = document.getElementById("cupcake-addition");
    const quantityElement = document.getElementById("cupcake-quantity");
    const quantityInput = document.getElementById("cupcakeQuantity");

    subtractButton.addEventListener("click", function () {
        if (quantity > 1) {
            quantity--;
            quantityElement.textContent = quantity;
            quantityInput.value = quantity;
            updateTotalPrice();
        }
    });

    addButton.addEventListener("click", function () {
        quantity++;
        quantityElement.textContent = quantity;
        quantityInput.value = quantity;
        updateTotalPrice();
    });

    // Initial total price update
    updateTotalPrice();
});
