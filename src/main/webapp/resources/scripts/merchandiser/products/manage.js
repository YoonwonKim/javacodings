sessionStorage.setItem("debugMode", true);
const DEBUG_MODE = sessionStorage.getItem("debugMode");
import {editor} from './editor.js';

$().ready(() => {

    // * Set Modal by Item info


    editor.setEvents();
})
