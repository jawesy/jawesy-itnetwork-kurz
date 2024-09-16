document.addEventListener('DOMContentLoaded', function() {
    // Dynamické zpracování služeb asistence na základě pojišťovacích limitů
    function updateAssistanceServiceOptions(insuranceType) {
        const insuranceLimit = document.getElementById(`${insuranceType}Limit`).value;
        const assistanceService = document.getElementById(`${insuranceType}AssistanceService`);

        assistanceService.innerHTML = '';

        if (insuranceLimit === 'basic') {
            assistanceService.innerHTML += '<option value="basic">Basic</option>';
        } else if (insuranceLimit === 'pro') {
            assistanceService.innerHTML += '<option value="pro">PRO</option>';
        }

        assistanceService.selectedIndex = 0;
        assistanceService.disabled = false;
    }

    // Kontrola, zda jsou všechny formuláře platné a podle toho aktivace/deaktivace tlačítka odeslat
    const submitButton = document.querySelector('button[type="submit"]');
    const forms = document.querySelectorAll('form');

    forms.forEach(function(form) {
        form.addEventListener('input', function() {
            submitButton.disabled = !form.checkValidity();
        });
    });

    // Bootstrap carousel pro slidery
    var myCarousel = document.querySelector('#carouselExample');
    var carousel = new bootstrap.Carousel(myCarousel, {
        interval: 5000,  // Změní čas mezi slidy na 5 sekund
        wrap: true       // Umožní nekonečné procházení
    });

    // Validace checkboxu pro souhlas s osobními údaji
    function validateConsent() {
        var consentCheckbox = document.getElementById('consent');
        if (!consentCheckbox.checked) {
            alert('Bez souhlasu se zpracováním osobních údajů sjednat pojištění nelze.');
            return false;
        }
        return true;
    }

    // Při změně pojišťovacího limitu zaktualizujeme dostupné služby asistence
    const insuranceTypes = ['crash', 'mandatory', 'property']; // Typy pojištění
    insuranceTypes.forEach(function(insuranceType) {
        const limitDropdown = document.getElementById(`${insuranceType}Limit`);
        if (limitDropdown) {
            limitDropdown.addEventListener('change', function() {
                updateAssistanceServiceOptions(insuranceType);
            });
        }
    });

    // Funkce pro potvrzení před smazáním
    function confirmDelete() {
        return confirm("Opravdu chcete tuto položku odstranit?");
    }

    // Přidání funkce potvrzení na všechny formuláře pro smazání
    const deleteForms = document.querySelectorAll('form.delete-confirmation');
    deleteForms.forEach(function(form) {
        form.addEventListener('submit', function(event) {
            if (!confirmDelete()) {
                event.preventDefault(); // Zastaví odeslání formuláře, pokud není smazání potvrzeno
            }
        });
    });
});

