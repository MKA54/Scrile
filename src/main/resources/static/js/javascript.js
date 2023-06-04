new Vue({
    el: "#modalApp",
    data: {
        buttonState: "",
        modalState: "none",
        firstName: "",
        lastName: "",
        email: "",
        productType: "",
        productPrice: {},
        additional100: false,
        additional200: false,
        comment: "",
        totalPrice: 0,
        amountAdditionalOptions: 0,
        selectedProductCost: 0,
        validation: false,
        serverValidation: false,
        serverError: ""
    },
    methods: {
        openModal: function () {
            this.buttonState = "none";
            this.modalState = "";
            this.serverValidation = false;
        },
        closeModal: function () {
            this.modalState = "none";
            this.buttonState = "";
            this.clear();
        },
        sendForm: function () {
            if (this.hasError) {
                this.validation = true;
                this.serverValidation = false;
                return;
            }

            let form = {
                firstName: this.firstName,
                lastName: this.lastName,
                email: this.email,
                productType: this.productType,
                comment: this.comment,
                selectedProductCost: this.selectedProductCost,
                amountAdditionalOptions: this.amountAdditionalOptions,
                totalPrice: this.totalPrice
            }

            let self = this;
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/form/api/v1/addForm",
                contentType: "application/json",
                data: JSON.stringify(form)
            }).done(function () {
                self.serverValidation = false;
            }).fail(function (ajaxRequest) {
                let formValidation = JSON.parse(ajaxRequest.responseText);
                self.serverError = formValidation.error;
                self.serverValidation = true;
            });

            this.clear();
            this.closeModal();
        },
        addItemPrice: function () {
            this.totalPrice -= this.selectedProductCost;

            if (this.productType === "monitor") {
                this.selectedProductCost = this.productPrice.monitor;
            }
            if (this.productType === "laptop") {
                this.selectedProductCost = this.productPrice.laptop;
            }
            if (this.productType === "phone") {
                this.selectedProductCost = this.productPrice.phone;
            }
            if (this.productType === "") {
                this.selectedProductCost = 0;
            }

            this.totalPrice += this.selectedProductCost;
        },
        add100: function () {
            if (this.additional100) {
                this.totalPrice += 100;
                this.amountAdditionalOptions += 100;
                return;
            }
            this.totalPrice -= 100;
            this.amountAdditionalOptions -= 100;
        },
        add200: function () {
            if (this.additional200) {
                this.totalPrice += 200;
                this.amountAdditionalOptions += 200;
                return;
            }
            this.totalPrice -= 200;
            this.amountAdditionalOptions -= 200;
        },
        clear: function () {
            this.firstName = "";
            this.lastName = "";
            this.email = "";
            this.productType = "";
            this.comment = "";
            this.selectedProductCost = "";
            this.additional100 = false;
            this.additional200 = false;
            this.totalPrice = 0;
        },
        loadPrice: function () {
            let self = this;
            $.get("http://localhost:8080/form/api/v1/getPrice").done(function (priceServer) {
                self.productPrice = priceServer;
            });
        }
    },
    computed: {
        firstNameError: function () {
            if (!this.firstName) {
                console.log("The name field must be filled in.")
                return {
                    message: "The name field must be filled in.",
                    error: true
                };
            }
            return {
                message: "",
                error: false
            };
        },
        lastNameError: function () {
            if (!this.lastName) {
                console.log("The last name field must be filled in.")
                return {
                    message: "The last name field must be filled in.",
                    error: true
                };
            }
            return {
                message: "",
                error: false
            };
        },
        emailError: function () {
            if (!this.email) {
                console.log("The email field must be filled in.")
                return {
                    message: "The email field must be filled in.",
                    error: true
                };
            }

            let EMAIL_REGEXP = /[a-z\d!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z\d!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z\d](?:[a-z\d-]*[a-z\d])?\.)+[a-z\d](?:[a-z\d-]*[a-z\d])?/;
            if (!EMAIL_REGEXP.test(String(this.email))) {
                console.log("Incorrect email format entered, example - user@gmail.com.")
                return {
                    message: "Incorrect email format entered, example - user@gmail.com.",
                    error: true
                };
            }
            return {
                message: "",
                error: false
            };
        },
        productTypeError: function () {
            if (!this.productType) {
                console.log("Select product type.")
                return {
                    message: "Select product type.",
                    error: true
                };
            }
            return {
                message: "",
                error: false
            };
        },
        hasError: function () {
            return this.lastNameError.error || this.firstNameError.error || this.emailError.error || this.productTypeError.error;
        }
    },
    created: function () {
        this.loadPrice();
    }
});
