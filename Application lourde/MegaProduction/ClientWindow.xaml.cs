using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using MegaProductionDBLIB;

namespace MegaProduction
{
    /// <summary>
    /// Logique d'interaction pour ClientWindow.xaml
    /// </summary>
    public partial class ClientWindow : Window
    {
        MegaCastingsEntities db = new MegaCastingsEntities();

        public ClientWindow(MegaCastingsEntities context)
        {
            InitializeComponent();
        }

        private void MN_Ajouter_Click(object sender, RoutedEventArgs e)
        {
            InformationClientWindow informationClientWindow = new InformationClientWindow(db);

            if (informationClientWindow.ShowDialog() == true)
            {

            }
        }

        private void MN_Modifier_Click(object sender, RoutedEventArgs e)
        {

        }

        private void MN_Supprimer_Click(object sender, RoutedEventArgs e)
        {

        }

        private void MN_Fermer_Click(object sender, RoutedEventArgs e)
        {
            Close();
        }
    }
}
