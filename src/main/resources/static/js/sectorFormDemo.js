// TODO: change this when running on external Tomcat server. See application.properties
let apiURL = "/sector_form_demo/";

function submitFormDataButtonClick() {
    let name = document.getElementById("nameInputField").value;
    let selectedSectorsArrayString = getSelectedSectorsArrayAsString();
    let termsChecked = document.getElementById("termsCheckbox").checked;

    if (!checkFormDataValidity(name, selectedSectorsArrayString, termsChecked)) {
        changeButtonColorOnInvalidFormDataSend();
        return;
    }
    changeButtonColorOnValidFormDataSend();
    // create json object to send as request body to api
    let dataJson = {
        userName: name,
        selectedSectors: selectedSectorsArrayString,
        agreeToTerms: "yes"
    };
    saveFormData(dataJson);
}

function checkFormDataValidity(name, selectedSectorsArrayString, termsChecked) {
    // check input user validity
    if (name.length === 0) {
        alert("Name field must not be empty!");
        return false;
    }
    if (selectedSectorsArrayString.length === 0) {
        alert("At least one sector must be selected!");
        return false;
    }
    if (!termsChecked) {
        alert("Terms must be agreed to!");
        return false;
    }
    return true;
}

function changeButtonColorOnInvalidFormDataSend() {
    document.getElementById("saveButton").style.background = "pink";
    setTimeout(function () {
        document.getElementById("saveButton").style.background = "lightgrey";
    }, 1000);
}

function changeButtonColorOnValidFormDataSend() {
    document.getElementById("saveButton").style.background = "mediumspringgreen";
    setTimeout(function () {
        document.getElementById("saveButton").style.background = "lightgrey";
    }, 1000);
}

function saveFormData(dataJson) {
    let url = apiURL + "saveUserInfo"; // must be right url. url is different on embedded TC and external TC
    fetch(url, {
        body: JSON.stringify(dataJson),
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        }
    });
}

function getSelectedSectorsArrayAsString() {
    let array = [];
    Array.from(document.querySelector("#sectorBox").options).forEach(function (option_element) {
        let option_value = option_element.value;
        let is_option_selected = option_element.selected;
        if (is_option_selected) {
            array.push(option_value);
        }
    });
    return array.toString();
}

function setupBody() {
    // create dual list box
    // see https://www.virtuosoft.eu/code/bootstrap-duallistbox/ and
    // https://www.jqueryscript.net/form/Responsive-jQuery-Dual-Select-Boxes-For-Bootstrap-Bootstrap-Dual-Listbox.html
    var demoSelector = $('select[name="duallistbox_demo"]').bootstrapDualListbox({
        nonSelectedListLabel: 'Not selected',
        selectedListLabel: 'Selected',
        preserveSelectionOnMove: 'moved',
        moveOnSelect: false
    });
    fillFormWithDataFromDatabase(demoSelector);
}

function fillFormWithDataFromDatabase(selector) {
    let url = apiURL + "getUserInfo";
    fetch(url, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        }
    }).then(
        response => {
            // returns Promise
            return response.json();
        })
        .then((response) => {
            // now it'a JSON not Promise

            document.getElementById("nameInputField").value = response.userName;
            if (response.agreeToTerms === "yes") {
                document.getElementById("termsCheckbox").checked = true;
            }
            fillSelectedOptions(response, selector);
            fillNotSelectedOptions(response, selector);

            selector.bootstrapDualListbox("refresh");
        });
}

function fillSelectedOptions(response, selector) {
    // iterate through list of selected sectors
    for (let index in response.selectedSectors) {
        let sectorName = response.selectedSectors[index].name;
        let sectorValue = response.selectedSectors[index].value;
        let sectorLevel = response.selectedSectors[index].level;
        let sectorText = "->".repeat(sectorLevel) + sectorName;
        selector.append("<option value='" + sectorValue + "' selected='selected'>" + sectorText + "</option>)");
    }
}

function fillNotSelectedOptions(response, selector) {
    // iterate through list of not selected sectors
    for (let index in response.notSelectedSectors) {
        let sectorName = response.notSelectedSectors[index].name;
        let sectorValue = response.notSelectedSectors[index].value;
        let sectorLevel = response.notSelectedSectors[index].level;
        let sectorText = "->".repeat(sectorLevel) + sectorName;
        selector.append("<option value='" + sectorValue + "'>" + sectorText + "</option>)");
    }
}