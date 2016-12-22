<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<spring:message code="label_index_title" var="label_index_title" />

<h4 class="text-center page-header">${label_index_title}</h4>

    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
            <li data-target="#carousel-example-generic" data-slide-to="3"></li>
            <li data-target="#carousel-example-generic" data-slide-to="4"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <img src="http://placehold.it/350x350"  alt="picture"/>
                <div>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. A, adipisci architecto beatae deserunt distinctio facere
                        laboriosam molestiae mollitia, nesciunt, nisi nobis officiis quidem quis quisquam quod repellendus similique totam voluptatibus?
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. A, adipisci architecto beatae deserunt distinctio facere
                        laboriosam molestiae mollitia, nesciunt, nisi nobis officiis quidem quis quisquam quod repellendus similique totam voluptatibus?
                    </p>
                </div>
            </div>
            <div class="item">
                <img src="http://placehold.it/350x350/ff0000"  alt="picture"/>
                <div>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. A, adipisci architecto beatae deserunt distinctio facere
                        laboriosam molestiae mollitia, nesciunt, nisi nobis officiis quidem quis quisquam quod repellendus similique totam voluptatibus?
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. A, adipisci architecto beatae deserunt distinctio facere
                        laboriosam molestiae mollitia, nesciunt, nisi nobis officiis quidem quis quisquam quod repellendus similique totam voluptatibus?
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. A, adipisci architecto beatae deserunt distinctio facere
                        laboriosam molestiae mollitia, nesciunt, nisi nobis officiis quidem quis quisquam quod repellendus similique totam voluptatibus?
                    </p>
                </div>
            </div>

            <div class="item">
                <img src="http://placehold.it/350x350/00ff00" alt="picture" />
                <div>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. A, adipisci architecto beatae deserunt distinctio facere
                        laboriosam molestiae mollitia, nesciunt, nisi nobis officiis quidem quis quisquam quod repellendus similique totam voluptatibus?
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. A, adipisci architecto beatae deserunt distinctio facere
                        laboriosam molestiae mollitia, nesciunt, nisi nobis officiis quidem quis quisquam quod repellendus similique totam voluptatibus?
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. A, adipisci architecto beatae deserunt distinctio facere
                        laboriosam molestiae mollitia, nesciunt, nisi nobis officiis quidem quis quisquam quod repellendus similique totam voluptatibus?
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. A, adipisci architecto beatae deserunt distinctio facere
                        laboriosam molestiae mollitia, nesciunt, nisi nobis officiis quidem quis quisquam quod repellendus similique totam voluptatibus?
                    </p>
                </div>

            </div>

            <div class="item">
                <img src="http://placehold.it/350x350/0000ff"  alt="picture"/>
                <div>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. A, adipisci architecto beatae deserunt distinctio facere
                        laboriosam molestiae mollitia, nesciunt, nisi nobis officiis quidem quis quisquam quod repellendus similique totam voluptatibus?
                    </p>
                </div>
            </div>

            <div class="item">
                <img src="http://placehold.it/350x350"  alt="picture"/>
                <div>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. A, adipisci architecto beatae deserunt distinctio facere
                        laboriosam molestiae mollitia, nesciunt, nisi nobis officiis quidem quis quisquam quod repellendus similique totam voluptatibus?
                    </p>
                </div>
            </div>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>

