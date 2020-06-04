function passValue() {

        // var name = document.getElementById('name').value;

        localStorage.setItem("hostelName", "SVBH");
        alert("done");
        // window.location.href = "hostel.html";
}

function getValue()
{

    var name = localStorage.getItem("hostelName");

    document.getElementById('hostel_name').innerHTML = name;

}