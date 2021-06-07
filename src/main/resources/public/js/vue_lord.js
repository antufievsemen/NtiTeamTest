var lordsApi = Vue.resource('/lord{/id}');

Vue.component('lord-form', {
    props: ['lords'],
    data: function () {
        return {
            lord_name: '',
            lord_years: ''
        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="Write name" v-model="lord_name">' +
        '<input type="text" placeholder="Write years" v-model="lord_years">' +
        '<input type="button" value="Save" @click="save"/>' +
        '</div>',
    methods: {
        save: function () {
            var lord = {
                name: this.lord_name,
                years: this.lord_years
            }
            lordsApi.save({}, lord).then(result =>
                result.json().then(data => {
                    this.lords.push(data)

                    this.lord_name = '';
                    this.lord_years = '';
                }));
        }
    }
});


Vue.component('lord-row', {
    props: ['lord', 'lords'],
    template:
        '<div> ' +
        '<i>' +
        'ID: ({{ lord.id }}), ' +
        '</i> ' +
        'Name: {{ lord.name }}, ' +
        'Years: {{ lord.years }}, ' +
        '</div>'
});

Vue.component('lords-list', {
    props: ['lords'],
    data: function () {
        return {
            lord: null
        }
    },
    template:
        '<div>' +
        '<lord-form :lords="lords"/>' +
        '<lord-row v-for="lord in lords" :key="lord.id" :lord="lord" :lords="lords"/>' +
        '</div>',
    created: function () {
        lordsApi.get().then(result =>
            result.json().then(data =>
                data.forEach(lord => this.lords.push(lord))
            )
        )
    }
});


var lordList = new Vue({
    el: '#lord_id',
    template: '<lords-list :lords="lords"/>',
    data: {
        lords: []
    }
});