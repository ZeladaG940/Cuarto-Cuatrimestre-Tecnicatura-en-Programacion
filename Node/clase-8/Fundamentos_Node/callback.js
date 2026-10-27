function soyAsincrona(micallback) { // 1. Agregamos el parámetro
    setTimeout(function() { // 2. Quitamos el parámetro de aquí
        console.log("Hola, soy una funcion asincrona");
        micallback();
    }, 1000);
}

console.log("Iniciando el proceso...");

soyAsincrona(function() {
    console.log("Terminando el proceso...");
});


function hola(nombre, micallback) {
    setTimeout(function() {
        console.log("Hola " + nombre);
        micallback(nombre);
    }, 1000);
}

function adios(nombre, micallback) {
    setTimeout(function() {
        console.log("Adios " + nombre);
        micallback();
    }, 1500);
}

console.log("Iniciando el proceso...");

hola("Carlos", function(nombreRecibido) { // 5. Recibimos el nombre
    // 6. Corregimos la sintaxis: en vez de 'nombre + function', usamos una coma
    adios(nombreRecibido, function() { 
        console.log("Terminando el proceso...");
    });
});

// adios('Carlos', function(){};); 
// adios('Carlos', function(){});