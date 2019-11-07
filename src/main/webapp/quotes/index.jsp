<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>

<h2>Quote Database</h2>

<c:if test="${error != null}"><p style="color: red;"><b><c:out value="${error}" /></b></p></c:if>

<h3>Your session</h3>

<h4>My Quotes</h4>
<ul>
    <c:forEach items="${myQuotes}" var="quote">
        <li>
            <form method="post" action="/quotes/delete">
                <input type="checkbox" name="delete" onclick="submit()" value="${quote.id}">
                <b><c:out value="${quote.author}"/></b> said <i>"${quote.text}"</i>
            </form>
        </li>
    </c:forEach>
</ul>

<form method="post" action="/quotes/deleteAll">
    <input type="checkbox" name="delete" onclick="submit()">
    Delete all of my quotes
</form>


<h4>Add a quote</h4>
<form method="post" action="/quotes/add">
    Add quote:
    <input style="display: block; margin-top: 20px; margin-bottom: 20px;" type="text" name="author" placeholder="Author">
    <textarea placeholder="Quote text" rows="3" style="display: block; margin-top: 20px; margin-bottom: 20px; width: 25%" name="quote"></textarea>
    <input type="submit" name="action" value="Add">
</form>


<h3>All Quotes</h3>
<ul>
    <c:forEach items="${allQuotes}" var="quote">
        <li>
            <b><c:out value="${quote.author}"/></b> said <i>"${quote.text}"</i>
        </li>
    </c:forEach>
</ul>


<p>There is a total of <c:out value="${quoteTotal}">0</c:out> quotes so far.</p>

</body>
</html>
