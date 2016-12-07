/**
 * Created by Dark on 04.12.2016.
 */

function setCommentId(k) {
    var parent_link = document.getElementById('parentLink');
    parent_link.value = k;

    var input = document.getElementById('commentField');
    input.type='text';
    input.focus();

}
