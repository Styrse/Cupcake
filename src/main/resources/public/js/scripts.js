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

document.querySelectorAll(".menu").forEach(menu => {
    menu.addEventListener("click", function () {
        let img = menu.querySelector("img");
        if (img && img.hasAttribute("href")) {
            let targetElement = document.getElementById(img.getAttribute("href"));

            if (targetElement) {
                targetElement.scrollIntoView({behavior: "smooth"});
            }
        }
    });
});

document.addEventListener('DOMContentLoaded', () => {
    let bottomId = null;
    let topId = null;

    const previewImg = document.getElementById('cupcake-preview-img');

    document.querySelectorAll('.cupcakeBottomRadio').forEach(input => {
        input.addEventListener('change', (e) => {
            bottomId = parseInt(e.target.value);
            updateImage();
        });
    });

    document.querySelectorAll('.cupcakeTopRadio').forEach(input => {
        input.addEventListener('change', (e) => {
            topId = parseInt(e.target.value);
            updateImage();
        });
    });

    function updateImage() {
        if (bottomId !== null && topId !== null) {
            const imageNumber = (bottomId - 1) * 9 + topId;
            previewImg.src = `/img/icons/cupcakes/${imageNumber}.png`;
        }
    }
});


