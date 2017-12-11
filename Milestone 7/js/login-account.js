

function validateForm() {

    var fields = document.forms["login-account"];

    var valid = true;


    for (i = 0; i < fields.length; i++) {
        if (fields[i].className.localeCompare("inputfield") == 0){
            document.getElementById(fields[i].name + "-validation").innerText = "";
        }
    }
    if (fields["password"].value.length < 5 || fields["password"].value.length > 15) {
        document.getElementById("password-validation").innerText = "Passwords must be between 5 and 15 characters";
        valid = false;
    }

    if (fields["email"].value.length > 30) {
        document.getElementById("email-validation").innerText = "Email address can only be 30 characters";
        valid = false;
    }

    for (i = 0; i < fields.length; i++) {
        if (fields[i].value == "") {
            document.getElementById(fields[i].name + "-validation").innerText = "* Required field";
            valid = false;
        }
    }




    return valid;

}













