function studentDetails(student) {
}

function addStudent() {

    var regNo = window.prompt("Enter registration number of student");

    var hostelId = document.getElementById("hostelId").value;
    var room = document.getElementById("room").value;

    window.location.href = "/admin/home/" + hostelId + "/hostel/" + room + "/room/" + regNo;
}
