<template>
<nav class="navbar navbar-expand-lg navbar-light bg-light" v-bind:class=" { 'navbarOpen': show }">
        <a class="navbar-brand" href="#">Tournament Elevator</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" @click.stop="toggleNavbar()">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent" v-bind:class="{ 'show': show }">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/teams/create">Create Team</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      Tournaments
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#">View Tournaments</a>
                        <a class="dropdown-item" href="/tournament/create">Create Tournaments</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#">My Tournaments</a>
                    </div>
                    </li>
            </ul>
            <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
             <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
        </div>
    </nav>
</template>

<script>
export default {
    name: "NavBar"
}
</script>

<style>

</style>