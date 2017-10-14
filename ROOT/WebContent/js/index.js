$(document).ready(function() {
    $('#id-btn-canal-babycaprichitos').bind('click', function(){
        if(  document.location.hostname === 'www.recyclothes.cl') {
            console.log('redirect --> '+'/index2.jsp');
            window.location.replace('index2.jsp');
        }else{
            console.log('redirect localhost --> '+window.location.protocol+'//' + document.location.host+ '/index2.jsp');
            window.location.replace(window.location.protocol+'//' + document.location.host+ '/index2.jsp');
        }
    });
});