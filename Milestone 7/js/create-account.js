

function validateForm() {

    var fields = document.forms["create-account"];

    var valid = true;


    for (i = 0; i < fields.length; i++) {
        if (fields[i].className.localeCompare("inputfield") == 0){
            document.getElementById(fields[i].name + "-validation").innerText = "";
        }
    }
    if (fields["password"].value.length < 5 || fields["password"].value.length > 15) {
        document.getElementById("password-validation").innerText = "Passwords must be between 5 and 15 characters";
        document.getElementById("password2-validation").innerText = "Passwords must be between 5 and 15 characters";
        valid = false;
    }
    if (fields["password"].value != fields["password2"].value) {

        document.getElementById("password-validation").innerText = "Passwords do not match";
        document.getElementById("password2-validation").innerText = "Passwords do not match";
        valid = false;
    }
    if (!validateEmail(fields["email"].value)) {
        document.getElementById("email-validation").innerText = "Email address not valid";
        valid = false;
    }

    if (fields["email"].value.length > 30) {
        document.getElementById("email-validation").innerText = "Email address can only be 30 characters";
        valid = false;
    }

    if (fields["firstname"].value.length > 15) {
        document.getElementById("firstname-validation").innerText = "First name can only be 15 characters long";
        valid = false;
    }
    if (fields["lastname"].value.length > 15) {
        document.getElementById("lastname-validation").innerText = "Last name can only be 15 characters long";
        valid = false;
    }


    if (!validateName(fields["firstname"].value)) {
        document.getElementById("firstname-validation").innerText = "First name invalid";
        valid = false;
    }

    if (!validateName(fields["lastname"].value)) {
        document.getElementById("lastname-validation").innerText = "Last name invalid";
        valid = false;
    }

    for (i = 0; i < fields.length; i++) {
        if (fields[i].value == "" && fields[i].className.localeCompare("inputfield") == 0) {
            document.getElementById(fields[i].name + "-validation").innerText = "* Required field";
            valid = false;
        }

    }


    return valid;



}

function validateEmail(email) {
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

function validateName(name) {
    var nameRegex = /^[a-zA-Z\-]+$/;
    return nameRegex.test(name);

}














