var planetsApi = Vue.resource('/planet{/id}');

function getIndex(list, id) {
    for (var i = 0; i < list.length; i++) {
        if (list[i].id === id) {
            return i;
        }
    }
    return -1;
}

Vue.component('planet-form', {
    props: ['planets', 'planetAttr'],
    data: function () {
        return {
            id: '',
            planet_name: '',
            planet_lord_id: ''
        }
    },
    watch: {
        planetAttr: function (newVal, oldVal) {
            this.id = newVal.id;
            this.planet_name = newVal.name;
            if (newVal.lord != null) {
                this.planet_lord_id = newVal.lord.id;
            }

        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="Write name" v-model="planet_name">' +
        '<input type="text" placeholder="Write id lord" v-model="planet_lord_id">' +
        '<input type="button" value="Save" @click="save"/>' +
        '</div>',
    methods: {
        save: function () {
            if (this.planet_lord_id) {
                var planet = {
                    name: this.planet_name,
                    lord: {
                        id: this.planet_lord_id
                    }
                }

            } else {
                var planet = {
                    name: this.planet_name,
                    lord: null
                }
            }
            if (this.id) {
                planetsApi.update({id: this.id}, planet).then(result =>
                    result.json().then(data => {
                            var index = getIndex(this.planets, data.id);
                            this.planets.splice(index, 1, data);
                            this.id = '';
                            this.planet_name = '';
                            this.planet_lord_id = '';
                        }
                    )
                )
            } else {
                planetsApi.save({}, planet).then(result =>
                    result.json().then(data => {
                        this.planets.push(data);
                        this.id = '';
                        this.planet_name = '';
                        this.planet_lord_id = '';
                    })
                )
            }
        }
    }
})
;

Vue.component('planet-row', {
    props: ['planet', 'editPlanet', 'planets'],
    data: function () {
        return {
            lord_isNull: true
        }
    },
    template:
        '<div> ' +
        '<i>' +
        'ID: ({{ planet.id }}), ' +
        '</i> ' +
        '<div>' +
        'Planet name: {{ planet.name }}, ' +
        '</div>' +
        '<div v-if="lord_isNull">' +
        'Planet Lord name: {{ planet.lord.name }}, ' +
        '</div>' +
        '<div v-else>' +
        '<input type="button" value="Edit" @click="editButton"/>' +
        '</div>' +
        '<input type="button" value="Delete" @click="delButton"/>' +
        '</div>',
    created: function () {
        if (this.planet.lord == null) {
            this.lord_isNull = false;
        }
    },
    methods: {
        editButton: function () {
            this.editPlanet(this.planet);
        },
        delButton: function () {
            planetsApi.remove({id: this.planet.id}).then(result => {
                if (result.ok) {
                    this.planets.splice(this.planets.indexOf(this.planet), 1);
                }
            })
        }
    }
});

Vue.component('planets-list', {
    props: ['planets'],
    data: function () {
        return {
            planet: null
        }
    },
    template:
        '<div>' +
        '<planet-form :planets="planets" :planetAttr="planet"/>' +
        '<planet-row v-for="planet in planets" :key="planet.id" :planet="planet" :editPlanet="editPlanet" :planets="planets"/>' +
        '</div>',
    created: function () {
        planetsApi.get().then(result =>
            result.json().then(data =>
                data.forEach(planet => this.planets.push(planet))
            )
        )
    },
    methods: {
        editPlanet: function (planet) {
            this.planet = planet;
        }
    }
});


var planetList = new Vue({
    el: '#planet_id',
    template: '<planets-list :planets="planets"/>',
    data: {
        planets: []
    }
});