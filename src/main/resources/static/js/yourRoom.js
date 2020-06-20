function addRoommates() {
    var roommates = document.getElementById("roommates").value;

    for (var i = 1; i <= 5; i++) {
        var cardElement = document.createElement("div");
        cardElement.setAttribute("class", "card");
        cardElement.setAttribute("style", "width: 18rem;");

        var image = document.createElement("img");
        image.src = "https://source.unsplash.com/1400x900/?nature,water";
        image.setAttribute("class", "card-img-top");

        var cardBody = document.createElement("div");
        cardBody.setAttribute("class", "card-body");

        var h5 = document.createElement("h5");
        var text = document.createTextNode("Roommate: " + i);
        h5.appendChild(text);

        var p = document.createElement("p");
        var pText = document.createTextNode("This is roommate: " + i);
        p.appendChild(pText);

        cardBody.appendChild(h5);
        cardBody.appendChild(p);

        cardElement.appendChild(image);
        cardElement.appendChild(cardBody);

        document.body.appendChild(cardElement);
    }
}