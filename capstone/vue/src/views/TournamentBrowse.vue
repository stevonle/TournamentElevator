<template>
<div>
    <BrowseTournaments/>
    <div class='filler'>
        </div>
</div>

</template>

<script>
import BrowseTournaments from "../components/BrowseTournaments.vue"


export default {
    name: 'Tournament-Browse',
    components: { BrowseTournaments },
    

}
</script>

<style>
.filler {
    margin: 0px auto 500px auto;
}

</style>