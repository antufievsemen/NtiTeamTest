var lordsYoungestApi = Vue.resource('/lord/topTenYoungestLords');
var lordsUselessApi = Vue.resource('/lord/uselessLords');

Vue.component('lord-row', {
    props: ['lord'],
    template:
        '<div> ' +
        '<i>' +
        'ID: ({{ lord.id }}), ' +
        '</i> ' +
        'Name: {{ lord.name }}, ' +
        'Years: {{ lord.years }}, ' +
        '</div>'
});

Vue.component('lordsYoungest-list', {
    props: ['lordsYoungest'],
    template:
        '<div>' +
        // '<div v-if="lordsYoungest != null">' +
        '<lord-row v-for="lord in lordsYoungest" :key="lord.id" :lord="lord" :lords="lordsYoungest"/>' +
        // '</div>' +
        '</div>',
    created: function () {
        lordsYoungestApi.get().then(result =>
            result.json().then(data =>
                data.forEach(lord => this.lordsYoungest.push(lord))
            )
        )
    }
});

Vue.component('lordsUseless-list', {
    props: ['lordsUseless'],
    template:
        '<div>' +
        '<lord-row v-for="lord in lordsUseless" :key="lord.id" :lord="lord" :lords="lordsUseless"/>' +
        '</div>',
    created: function () {
        lordsUselessApi.get().then(result =>
            result.json().then(data =>
                data.forEach(lord => this.lordsUseless.push(lord))
            )
        )
    }
});

var manage = new Vue({
    el: '#manage_id',
    template:
        '<div>' +
        '<h3>Useless lords</h3>' +
        '<lordsUseless-list :lordsUseless="lordsUseless"/>' +
        '<h3>Top 10 youngest lords</h3>' +
        '<lordsYoungest-list :lordsYoungest="lordsYoungest"/>' +
        '</div>',
    data: {
        lordsUseless: [],
        lordsYoungest: []
    }
});