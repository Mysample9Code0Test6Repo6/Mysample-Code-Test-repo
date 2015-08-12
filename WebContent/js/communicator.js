// DEPENDENCIES: JQuery 1.4.2.min
/*
	Contains all the functions related to displaying / managing messages in the communicator bar.
*/

// Configurable icon constants.
var communicator_errorIcon = "css/communicator/error_medium.png";
var communicator_generalIcon = "css/communicator/communicator_icon.png";

// jQuery initializer, do not alter.
$(document).ready(function() { communicator_init(); });

function communicator_init()
{
    communicator = $("#communicator");
    communicator.setMessage = communicator_setMessage;
    communicator.setIcon = communicator_setIcon;
}

// public variables.
var communicator;

/*
    Communicator API Functions

    API functions are defined first with the name of the "class",
    it signifies that our intent is that we won't call these functions
    directly, but rather through an object that implements them,
    in this case the "communicator" object.
    
    By "binding" all of these functions we accomplish two things:
    1) Logical grouping of all communicator based functions.
    2) We avoid function name collisions, it's possible to have multiple setMessage
        functions, each associated with a different js library.
*/

// usage: communicator.setMessage(message)
function communicator_setMessage(message)
{
    communicator.find("#communicatorMessage").text(message);
}

// usage: communicator.setIcon(type)
// valid types = "error", "general"
function communicator_setIcon(type)
{
    switch(type)
    {
        case "error":
            communicator.find(".communicatorIcon").attr("src", communicator_errorIcon);
            break;
        default:
            communicator.find(".communicatorIcon").attr("src", communicator_generalIcon);
            break;
    }
}