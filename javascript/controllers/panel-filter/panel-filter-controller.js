(function(){
  angular.module('lup1')
  .controller('PanelFilterController', function(){
    this.panelsFilter = panel;
    this

  });
  var panelsFilter = [
    {
      name:'Gestion des matieres',
      buttons:[
        {
          name:'Ajouter une matières',
          href:'#'
        }
      ],
      filters:[
        {
          label:'nom',
          name:'subject',
          type:'text',
          id:'name-subject',
          placeholder:'nom de la matière'
        },
        {
          label:'UE',
          name:'ue',
          type:'text',
          id:'ue-subject',
          placeholder:'UE de la matière'
        },
        {
          label:'Coeff',
          name:'coeff',
          type:'text',
          id:'coeff-subject',
          placeholder:'Coeff de la matière'
        },
        {
          label:'Responsable',
          name:'manager',
          type:'text',
          id:'manager-subject',
          placeholder:'Responsable de la matière'
        }
      ]
    }
  ],
  [
    {
      name:'Gestion des évaluations',
      buttons:[
        {
          name:'Ajouter une évaluation',
          href:'#'
        }
      ],
      filters:[
        {
          label:'nom',
          name:'subject',
          type:'text',
          id:'name-subject',
          placeholder:'nom de la matière'
        },
        {
          label:'UE',
          name:'ue',
          type:'text',
          id:'ue-subject',
          placeholder:'UE de la matière'
        },
        {
          label:'Coeff',
          name:'coeff',
          type:'text',
          id:'coeff-subject',
          placeholder:'Coeff de la matière'
        },
        {
          label:'Responsable',
          name:'manager',
          type:'text',
          id:'manager-subject',
          placeholder:'Responsable de la matière'
        }
      ]
    }
  ]


}
// var name ="Gestion des matieres";
// var button = {
//   name:'Ajouter une matières',
//   href:'#'
// }
// var filters = [
//   {
//     label:'nom',
//     name:'subject',
//     type:'text',
//     id:'name-subject',
//     placeholder:'nom de la matière'
//   },
//   {
//     label:'UE',
//     name:'ue',
//     type:'text',
//     id:'ue-subject',
//     placeholder:'UE de la matière'
//   },
//   {
//     label:'Coeff',
//     name:'coeff',
//     type:'text',
//     id:'coeff-subject',
//     placeholder:'Coeff de la matière'
//   },
//   {
//     label:'Responsable',
//     name:'manager',
//     type:'text',
//     id:'manager-subject',
//     placeholder:'Responsable de la matière'
//   }
// ]
// }
)();
