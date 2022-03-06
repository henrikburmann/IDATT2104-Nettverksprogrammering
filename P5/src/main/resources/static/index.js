function trykk(){
    console.log("mottatt")
    let tekst = document.getElementById("kode").value;

    console.log(tekst);
    document.querySelector("#utskrevetKode").innerHTML = tekst;
}