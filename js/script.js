var opt=0;
var state="";
var width=0,height=0;
$(document).ready(function()
{
$(".loader").hide();
header();

$(".menu a").click(function(event)
{
event.preventDefault();
var link=$(this).attr("href");
$('html,body').animate({scrollTop: $(link).offset().top},100);
});
initialSize();

});

/*Script for Google Map*/
var marker,map;
      function initialize() {

        var mapOptions = {
          center: new google.maps.LatLng(20.530892091775808, 77.8705625),
          zoom: 2,
	  apanControl: false,
  zoomControl: false,
  mapTypeControl: false,
  scaleControl: false,
  streetViewControl: false,
  overviewMapControl: false,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        map = new google.maps.Map(document.getElementById("map_canvas"),
            mapOptions);
google.maps.event.addListener(map, 'click', function(event) {
  placeMarker(event.latLng);
$(".select").hide();
$(".loader").show();
var latlng="latlng="+event.latLng.lat()+","+event.latLng.lng();
$.ajax(
{
type: "POST",
url: "GetCountry",
data: latlng,
success: function(result) {
$(".loader").hide();
    }
});
});

      }
function placeMarker(location) {
  if ( marker ) {
    marker.setPosition(location);
  } else {
    marker = new google.maps.Marker({
      position: location,
      map: map
    });
  }
}

