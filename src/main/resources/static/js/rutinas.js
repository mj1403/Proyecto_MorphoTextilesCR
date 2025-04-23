
function readURL (input){
    if(input.files && input.files[0]){
        var lector= new FileReader();
        lector.onload=function (e){
            
            $('#blah').attr('src',e.target.result)
                    .height(200);
            
        };
        lector.readAsDataURL(input.files[0]);
    }
}
function addCart(formulario) {
    var idProducto = formulario.elements[0].value;
    var existencias = formulario.elements[1].value;
    
    if (existencias > 0) {
        var ruta = "/carrito/agregar/" + idProducto;
        $("#resultBlock").load(ruta);//llama la ruta como si se diera enter en el url
        
    } else {
        window.alert("No hay existencias...");

    }
}