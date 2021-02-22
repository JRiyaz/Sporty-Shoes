$(document).ready(function () {
  const url = window.location.href;

  if (url.endsWith("sign-up"))
    document.getElementById("sign-up").addClass = "active";
  else if (url.endsWith("sign-in"))
    document.getElementById("sign-in").addClass = "active";

  if (url.endsWith("user/all")) {
      let admin = document.getElementById("admin");
      admin.addClass = "active";
      admin.childNodes.item('a').addClass = "active";
      document.getElementById("men-list").childNodes.item("li").addClass = "active";
      }
    else if (url.endsWith("sign-in"))
      document.getElementById("sign-in").addClass = "active";

  if (url.includes("?login-successful"))
    toast("Login Successful", "text-success", "border-success");
  else if (url.includes("?logout-successful"))
    toast("Logout Successful", "text-success", "border-success");
  else if (url.includes("?user-exists"))
    toast(
      "You should Logout first to Login again.",
      "text-danger",
      "border-danger"
    );

  function toast(message, textColor, borderColor) {
    const alert = $("#toast-alert");
    const body = $("#toast-body");

    body.addClass(textColor);
    body.text(message);

    alert.addClass(borderColor);
    alert.toast({ delay: 5000 });
    alert.toast("show");
  }
});
