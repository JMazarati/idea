/**
 * Created by Dark on 04.12.2016.
 */

function setCommentId(k) {
    document.getElementById('parentLink').value = k;

    var input = document.getElementById('commentField');
    input.type='text';
    input.focus();

}
